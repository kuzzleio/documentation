$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'extract_signature.rb'

module SectionMutator
  class Base
    def initialize
    end

    def mutate(section)
      section_mutator = "#{section.kind}_mutator".to_sym

      if respond_to?(section_mutator)
        global_mutator(public_send(section_mutator, section))
      else
        global_mutator(content_mutator(section.content))
      end
    end

    def content_mutator(content)
      content
    end

    def global_mutator(content)
      content
    end
  end

  class Csharp < Base
    REPLACE = {
      /std::vector<std::string>/                   => 'List<string>',
      /const std::string&/                         => 'string',
      /const std::string\*/                        => 'string',
      /const std::string/                          => 'string',
      /std::string/                                => 'string',
      /kuzzleio::query_options\*/                  => 'Kuzzleio::QueryOptions',
      /kuzzleio::query_options/                    => 'Kuzzleio::QueryOptions',
      /boolean/                                    => 'bool',
      /Boolean/                                    => 'bool',
      /kuzzleio::KuzzleException/                  => 'Kuzzleio::KuzzleException',
      /kuzzleio::SearchResult*/                    => 'Kuzzleio::SearchResult',
      /A pointer to a/                             => 'A',
      /delete_/                                    => 'delete',
      /struct/                                     => 'class'
    }

    def initialize()
      @signature_extractor = SignatureExtractor::Csharp.new
    end

    def signature_mutator(section)
      content = []

      content << "## Signature"
      content << ""
      content << "```csharp"
      content += @signature_extractor.extract(section.page.controller, section.page.action)
      content << "```"
      content << "\n"

      content.join("\n")
    end

    def content_mutator(content)
      common_replace(content)
    end

    def global_mutator(content)
      content.gsub(/cpp\/1/, 'csharp/1')
    end

    private

    def common_replace(content)
      REPLACE.each do |regexp, replace_value|
        content.gsub!(regexp, replace_value)
      end

      content
    end
  end
end
