require 'rbtrace'
require 'json'
require 'uri'
require 'typhoeus'

class LinkChecker
  INTERNAL_LINK_REGEXP = /\(\{\{\s+site_base_path\s+\}\}([^)]+)/

  def initialize(path)
    @path = path

    @hydra = Typhoeus::Hydra.new

    @dead_links = {
      internal: {},
      external: {}
    }
  end

  def run
    each_dir(ARGV[0]) do |file_path|
      next unless file_path.end_with?('.md')

      content = File.read(file_path)

      scan_internal_links(file_path, content)

      scan_external_links(file_path, content)
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
    end

    puts "Found #{@dead_links[:external].count} uniq external dead links:\n"
    @dead_links[:external].each do |link, pages|
      puts "  - #{link} found on #{pages.count} pages:"
      pages.each do |page|
        puts "    -> #{page}"
      end
    end
  end

  def report_json(path = './dead_links.json')
    File.write(path, JSON.pretty_generate(@dead_links))
  end

  private

  def scan_internal_links(file_path, content)
    match = content.scan(INTERNAL_LINK_REGEXP)
    match.each do |(relative_path)|
      # Remove anchor
      relative_path.gsub!(/#[\w-]+/, '')

      full_path = "src/#{relative_path}/index.md"
      # Remove double //
      full_path.gsub!(/\/\//, '/')

      next if File.exists?(full_path)

      @dead_links[:internal][full_path] ||= []
      @dead_links[:internal][full_path] << file_path
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
          @dead_links[:external][external_link] << file_path
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

if ARGV[0].to_s.empty?
  puts 'Usage: ruby check_link.rb <path>'
  exit 1
end

link_checker = LinkChecker.new(ARGV[0])

link_checker.run

link_checker.report_stdout
link_checker.report_json
