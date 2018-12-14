$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'section_mutator.rb'

module SnippetMutator

  class Csharp
    REPLACE = SectionMutator::Csharp::REPLACE.merge({
      /&/                                                => '',
      /->/                                               => '.',
      /Kuzzleio::/                                       => ''
    })
    STDOUT_FIND = /std::cout.*std::endl;/
    STDERR_FIND = /std::cerr.*std::endl;/
    STREAM_REPLACE = /[<<]([^<]+)[<<]/

    def mutate(content)
      common_replace(content)
      stdout_replace(content)
      stderr_replace(content)

      content
    end

    private

    def common_replace(content)
      REPLACE.each do |regexp, replace_value|
        content.gsub!(regexp, replace_value)
      end
    end

    def stdout_replace(content)
      stdout_lines = content.scan(STDOUT_FIND)

      stdout_lines.each do |line|
        match = line.scan(STREAM_REPLACE)
        string = match.join(" + ").squish

        content.gsub!(line, "Console.WriteLine(#{string});")
      end
    end

    def stderr_replace(content)
      stderr_lines = content.scan(STDERR_FIND)

      stderr_lines.each do |line|
        match = line.scan(STREAM_REPLACE)
        string = match.join(" + ").squish

        content.gsub!(line, "Console.Error.WriteLine(#{string});")
      end
    end

  end
end
