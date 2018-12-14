$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'fileutils'
require 'page.rb'
require 'test_file.rb'
require 'snippet.rb'

class String
  def camelcaselow
    self.split('_').inject([]){ |buffer,e| buffer.push(buffer.empty? ? e : e.capitalize) }.join
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

each_dir(ARGV[0]) do |file|
  if file.end_with?(".md")
    page = Page.new(file)
    page.parse
    page.copy_to('csharp', '1')
  elsif file.end_with?('.cpp')
    snippet = Snippet.new(file)
    snippet.copy_to('csharp', '1')
  elsif file.end_with?('.test.yml')
    test_file = TestFile.new(file)
    test_file.copy_to('csharp', '1')
  end
end
