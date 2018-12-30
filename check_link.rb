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
  internal: [],
  external: []
}

each_dir(ARGV[0]) do |file_path|
  next unless file_path.end_with?('.md')

  content = File.read(file_path)

  # Scan internal links
  match = content.scan(/\(\{\{\s+site_base_path\s+\}\}([^)]+)/)
  match.each do |(relative_path)|
    full_path = "src/#{relative_path}/index.md"
    next if File.exists?(full_path)

    dead_links[:internal] << {
      page: file_path,
      link: full_path
    }
  end

  # Scan external links
  external_links = URI.extract(content, ['http', 'https'])
  external_links.each do |external_link|
    # Remove markdown parenthesis and other garbage
    external_link.gsub!(/[\)][\.:,]*/, '')

    request = Typhoeus::Request.new(external_link, followlocation: true)

    request.on_complete do |response|
      if response.code != 200
        dead_links[:external] << {
          page: file_path,
          link: external_link
        }
      end
    end

    hydra.queue(request)
  end
end

hydra.run

puts "Found #{dead_links[:internal].count} internal dead links:\n"
dead_links[:internal].each do |dead_link|
  puts "  - on #{dead_link[:page]}: #{dead_link[:link]}"
end

puts "Found #{dead_links[:external].count} external dead links:\n"
dead_links[:external].each do |dead_link|
  puts "  - on #{dead_link[:page]}: #{dead_link[:link]}"
end
