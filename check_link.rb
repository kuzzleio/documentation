require 'json'
require 'uri'
require "typhoeus"

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

hydra = Typhoeus::Hydra.new

dead_links = {
  internal: {},
  external: {}
}

each_dir(ARGV[0]) do |file_path|
  next unless file_path.end_with?('.md')

  content = File.read(file_path)

  # Scan internal links
  match = content.scan(/\(\{\{\s+site_base_path\s+\}\}([^)]+)/)
  match.each do |(relative_path)|
    # Remove anchor
    relative_path.gsub!(/#[\w-]+/, '')
    full_path = "src/#{relative_path}/index.md"
    next if File.exists?(full_path)

    dead_links[:internal][full_path] ||= []
    dead_links[:internal][full_path] << file_path
  end

  # Scan external links
  external_links = URI.extract(content, ['http', 'https'])
  external_links.each do |external_link|
    # Remove markdown parenthesis and other garbage
    external_link.gsub!(/[\)][\.:,]*/, '')

    request = Typhoeus::Request.new(external_link, followlocation: true)

    request.on_complete do |response|
      if response.code != 200
        dead_links[:external][external_link] ||= []
        dead_links[:external][external_link] << file_path
      end
    end

    hydra.queue(request)
  end
end

hydra.run

File.write('./dead_links.json', JSON.pretty_generate(dead_links))

puts "Found #{dead_links[:internal].count} uniq internal dead links:\n"

dead_links[:internal].each do |link, pages|
  puts "  - #{link} found on #{pages.count} pages:"
  pages.each do |page|
    puts "    -> #{page}"
  end
end

puts "Found #{dead_links[:external].count} uniq external dead links:\n"
dead_links[:external].each do |link, pages|
  puts "  - #{link} found on #{pages.count} pages:"
  pages.each do |page|
    puts "    -> #{page}"
  end
end
