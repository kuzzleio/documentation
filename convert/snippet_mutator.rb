$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'section_mutator.rb'

module SnippetMutator

  class Csharp
    REPLACE = {
      /\.size\(\)/                                       => '.Count',
      /&/                                                => '',
      /->/                                               => '.',
      /push_back/                                        => 'Add',
      /kuzzleio::/                                       => '',
      /Kuzzleio::/                                       => '',
      /std::string/                                      => 'string',
      /query_options options;/                           => 'QueryOptions options = new QueryOptions();',
      /options options;/                                 => 'options options = new options();',
      /SearchResult\*/                                   => 'SearchResult',
      /validation_response \*validation_response/        => 'validation_response validation_response',
      /delete_\(/                                        => 'delete(',
      /token_validity\*/                                 => 'TokenValidity',
      /e.what\(\)/                                       => 'e.getMessage()',
      /size_t/                                           => 'uint',
      /WebSocket\*/                                      => 'WebSocket',
      /Kuzzle\*/                                         => 'Kuzzle',
      /kuzzle_response\*/                                => 'kuzzle_response'
    }
    STDOUT_FIND = /std::cout.*std::endl;/
    STDERR_FIND = /std::cerr.*std::endl;/
    STREAM_REPLACE = /[<<]([^<]+)[<<]/
    MULTILINE_STRING_REPLACE = /(R"\(([\[\n\{\s"\w:\-,\}\]]+)\)")/m
    VECTOR_INIT_REPLACE = /(std::vector<(.*)>\W+(\w+);)/
    VECTOR_ASSIGN_REPLACE = /(std::vector<(.*)>\W+(\w+))\s+=/
    UNIQUE_PTR_REPLACE = /(std::unique_ptr<([\w:]+)>)/
    SHARED_PTR_REPLACE = /(std::shared_ptr<([\w:]+)>)/
    FOR_LOOP_FIND = /for \(.*:.*/
    FOR_LOOP_REPLACE = /for \(auto ([\w]+) : ([\w]+)\) {/

    def mutate(content)
      common_replace(content)
      stdout_replace(content)
      stderr_replace(content)
      multiline_string_replace(content)
      vector_replace(content)
      unique_ptr_replace(content)
      shared_ptr_replace(content)
      for_loop_replace(content)

      content
    end

    private

    def multiline_string_replace(content)
      multiline_strings = content.scan(MULTILINE_STRING_REPLACE)

      multiline_strings.each do |(full_match, string_match)|
        string_match.gsub!('"', '""')
        content.gsub!(full_match, "@\"#{string_match}\"")
      end
    end

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

    def vector_replace(content)
      vector_init_lines = content.scan(VECTOR_INIT_REPLACE)

      vector_init_lines.each do |(line, var_type, var_name)|
        csharp_line = "List<#{var_type}> #{var_name} = new List<#{var_type}>();"
        content.gsub!(VECTOR_INIT_REPLACE, csharp_line)
      end

      vector_assign_lines = content.scan(VECTOR_ASSIGN_REPLACE)

      vector_assign_lines.each do |(line, var_type, var_name)|
        csharp_line = "List<#{var_type}> #{var_name} ="
        content.gsub!(VECTOR_ASSIGN_REPLACE, csharp_line)
      end
    end

    def unique_ptr_replace(content)
      unique_ptr_lines = content.scan(UNIQUE_PTR_REPLACE)

      unique_ptr_lines.each do |(line, var_type)|
        content.gsub!(line, var_type)
      end
    end

    def shared_ptr_replace(content)
      shared_ptr_lines = content.scan(SHARED_PTR_REPLACE)

      shared_ptr_lines.each do |(line, var_type)|
        content.gsub!(line, var_type)
      end
    end

    def for_loop_replace(content)
      for_loop_lines = content.scan(FOR_LOOP_FIND)

      for_loop_lines.each do |line|
        match = line.scan(FOR_LOOP_REPLACE)&.first
        byebug if match.nil?

        content.gsub!(line, "foreach (var #{match[0]} in #{match[1]}) {")
      end
    end


  end
end
