$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'signature_extractor.rb'

module SectionMutator
  class Base
    def initialize
    end

    def mutate(section)
      section_mutator = "#{section.kind}_mutator".to_sym

      if respond_to?(section_mutator)
        public_send(section_mutator, section)
      else
        content_mutator(section.content)
      end
    end

    def content_mutator(content)
      content
    end
  end

  class Csharp < Base
    REPLACE = {
      /std::vector<std::string>/                   => 'List<string>',
      /(const )?std::string[\*&]?/                 => 'string',
      /const char\s?[\\]?\*/                       => 'string',
      /char[\\]?\*/                                => 'string',
      /kuzzleio::query_options[\\]?\*/             => 'Kuzzleio::QueryOptions',
      /query_options[\\]?\*/                       => 'Kuzzleio::QueryOptions',
      /kuzzleio::KuzzleException/                  => 'Kuzzleio::KuzzleException',
      /kuzzleio::SearchResult*/                    => 'Kuzzleio::SearchResult',
      /A pointer to a/                             => 'A',
      /delete_/                                    => 'delete',
      /token_validity/                             => 'TokenValidity',
      /struct /                                    => 'class ',
      /cpp\/1/                                     => 'csharp/1',
      /cpp/                                        => 'csharp',
      /std::runtime_error/                         => 'System.ApplicationException',
      /unsigned int/                               => 'int',
      /const[\s]+/                                 => '',
      /kuzzleio::options[\\]?\*/                   => 'Options',
      /unsigned long( long)?/                      => 'long',
      /unsigned/                                   => 'int',
      /kuzzleio::room_options[\\]?\*/              => 'RoomOptions',
      /kuzzleio::NotificationListener[\*]?/        => 'NotificationListener',
      /kuzzleio::notification_result[\*]?/         => 'NotificationResult',
      /kuzzleio::notification_content[\*]?/        => 'NotificationContent',
      /kuzzleio::kuzzle_request[\\]?\*/            => 'Kuzzleio::KuzzleRequest',
      /kuzzleio::kuzzle_reresponse[\\]?\*/         => 'Kuzzleio::KuzzleResponse',
      /SearchResult[\\]?\*/                        => 'SearchResult',
      /[\s]*const[\s]*/                            => '',
      /what\(\)/                                   => 'getMessage()',
      /kuzzleio::/                                 => 'Kuzzleio::',
      /Protocol[\\]?\*/                            => 'Protocol'
    }

    def initialize
      @signature_extractor = SignatureExtractor::Csharp.new
    end

    def signature_mutator(section)
      content = []

      content << "## Signature"
      content << ""
      content << "```csharp"
      content +=
        if section.page.action.in?(['getters', 'setters'])
          @signature_extractor.extract(section.page.controller, section.up.title)
        else
          @signature_extractor.extract(section.page.controller, section.page.action)
        end
      content << "```"
      content << "\n"

      content.join("\n")
    end

    def content_mutator(content)
      common_replace(content)
    end

    private

    def common_replace(content)
      REPLACE.each do |regexp, replace_value|
        content.gsub!(regexp, replace_value)
      end

      content
    end
  end

  class Java < Base
    REPLACE = {
      /std::vector<std::string>/                   => 'io.kuzzle.sdk.StringVector',
      /(const )?std::string[\*&]?/                 => 'String',
      /const char\s?[\\]?\*/                       => 'String',
      /char[\\]?\*/                                => 'String',
      /kuzzleio::query_options[\\]?\*/             => 'io.kuzzle.sdk.QueryOptions',
      /query_options[\\]?\*/                       => 'io.kuzzle.sdk.QueryOptions',
      /bool/                                       => 'boolean',
      /kuzzleio::KuzzleException/                  => 'io.kuzzle.sdk.KuzzleException',
      /kuzzleio::SearchResult*/                    => 'io.kuzzle.sdk.SearchResult',
      /A pointer to a/                             => 'A',
      /delete_/                                    => 'delete',
      /token_validity/                             => 'io.kuzzle.sdk.TokenValidity',
      /struct /                                    => 'class ',
      /cpp\/1/                                     => 'java/1',
      /cpp/                                        => 'java',
      /std::runtime_error/                         => 'Throwable',
      /unsigned int/                               => 'int',
      /const[\s]+/                                 => '',
      /kuzzleio::options[\\]?\*/                   => 'io.kuzzle.sdk.Options',
      /unsigned long( long)?/                      => 'long',
      /unsigned/                                   => 'int',
      /kuzzleio::room_options[\\]?\*/              => 'io.kuzzle.sdk.RoomOptions',
      /kuzzleio::NotificationListener[\*]?/        => 'io.kuzzle.sdk.NotificationListener',
      /kuzzleio::notification_result[\*]?/         => 'io.kuzzle.sdk.NotificationResult',
      /kuzzleio::notification_content[\*]?/        => 'io.kuzzle.sdk.NotificationContent',
      /kuzzleio::kuzzle_request[\\]?\*/            => 'io.kuzzle.sdk.KuzzleRequest',
      /kuzzleio::kuzzle_reresponse[\\]?\*/         => 'io.kuzzle.sdk.KuzzleResponse',
      /SearchResult[\\]?\*/                        => 'io.kuzzle.sdk.SearchResult',
      /[\s]*const[\s]*/                            => '',
      /what\(\)/                                   => 'getMessage()',
      /kuzzleio::/                                 => 'io.kuzzle.sdk.',
      /Protocol[\\]?\*/                            => 'io.kuzzle.sdk.Protocol'
    }

    def initialize
      @signature_extractor = SignatureExtractor::Java.new
    end

    def signature_mutator(section)
      content = []

      content << "## Signature"
      content << ""
      content << "```java"
      content +=
        if section.page.action.in?(['getters', 'setters'])
          @signature_extractor.extract(section.page.controller, section.up.title)
        else
          @signature_extractor.extract(section.page.controller, section.page.action)
        end
      content << "```"
      content << "\n"

      content.join("\n")
    end

    def content_mutator(content)
      common_replace(content)
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
