require 'rbtrace'
require 'json'
require 'uri'
require 'typhoeus'
require 'optparse'

class LinkChecker
  INTERNAL_LINK_REGEXP = /\(\{\{\s+site_base_path\s+\}\}([^)>]+)/

  def initialize(options)
    @path = options[:path]
    @only = options[:only] || ''
    @json_file = options[:file] || './dead_links.json'

    @hydra = Typhoeus::Hydra.new

    @dead_links = {
      internal: {},
      external: {}
    }
  end

  def run
    each_dir(@path) do |file_path|
      next unless file_path.end_with?('.md')

      content = File.read(file_path)

      scan_internal_links(file_path, content) unless @only == 'external'

      scan_external_links(file_path, content) unless @only == 'internal'
    end

    @hydra.run
  end

  def report_stdout
    puts "Found #{@dead_links[:internal].count} uniq internal dead links:\n"
    @dead_links[:internal].each do |link, pages|
      puts "  - #{link} found on #{pages.count} pages:"
      pages.each do |page|
        puts "    -> #{page}"
      end
      puts ""
    end

    puts "Found #{@dead_links[:external].count} uniq external dead links:\n"
    @dead_links[:external].each do |link, pages|
      puts "  - #{link} found on #{pages.count} pages:"
      pages.each do |page|
        puts "    -> #{page}"
      end
    end
  end

  def report_json
    File.write(@json_file, JSON.pretty_generate(@dead_links))
  end

  private

  def scan_internal_links(file_path, content)
    match = content.scan(INTERNAL_LINK_REGEXP)
    match.each do |(relative_path)|
      # Remove anchor
      relative_path.gsub!(/#[\w-]+/, '')

      if relative_path.end_with?('.png')
        full_path = "src/#{relative_path}"
      else
        full_path = "src/#{relative_path}/index.md"
      end

      # Remove double //
      full_path.gsub!(/\/\//, '/')

      next if File.exists?(full_path)

      @dead_links[:internal][full_path] ||= []
      @dead_links[:internal][full_path] << file_path.gsub(/\/\//, '/')
    end
  end

  def scan_external_links(file_path, content)
    external_links = URI.extract(content, ['http', 'https'])
    external_links.each do |external_link|
      # Remove markdown parenthesis and other garbage
      external_link.gsub!(/[\)][\.:,]*/, '')

      request = Typhoeus::Request.new(external_link, followlocation: true)

      request.on_complete do |response|
        if response.code != 200
          @dead_links[:external][external_link] ||= []
          @dead_links[:external][external_link] << file_path.gsub(/\/\//, '/')
        end
      end

      @hydra.queue(request)
    end
  end

  def each_dir(start, &block)
    directories = Dir["#{start}/*"]

    directories.each do |path|
      if File.file?(path)
        block.call(path)
      else
        each_dir(path, &block)
      end
    end
  end
end

options = {}

OptionParser.new do |opt|
  opt.on('-p PATH') { |o| options[:path] = o }
  opt.on('--only TYPE') { |o| options[:only] = o }
  opt.on('--output FILE') { |o| options[:file] = o }
end.parse!

link_checker = LinkChecker.new(options)

link_checker.run

link_checker.report_stdout
link_checker.report_json
