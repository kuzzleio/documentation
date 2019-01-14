$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'

class SignatureExtractor
  def initialize(sig_regexp_evaluator, file_name_evaluator)
    @sig_regexp_evaluator = sig_regexp_evaluator
    @file_name_evaluator = file_name_evaluator
  end

  def extract(controller, action, &block)
    action = action.underscore.camelcaselow
    content = File.read("#{@path}/#{@file_name_evaluator.call(controller)}")

    match = content.scan(@sig_regexp_evaluator.call(controller, action))

    if match.empty?
      puts "Can't extract signature for #{controller} #{action}"
      return []
    end

    if block.nil?
      match.flatten.sort
    else
      match.flatten.sort.map(&block)
    end
  end
end

class SignatureExtractor::Csharp < SignatureExtractor
  SIGNATURE_REGEXP_EVALUATOR = -> (controller_name, action_name) do
    if action_name == 'constructor'
      /(public .*\s?#{controller_name.underscore.camelcase}\([^{]+)/
    else
      /(public .*\s?#{action_name}\([^{]+)/
    end
  end

  FILE_NAME_EVALUATOR = -> (controller_name) do
    if controller_name == 'websocket'
      'WebSocket.cs'
    else
      "#{controller_name.underscore.camelcase}.cs"
    end
  end

  def initialize(path = nil)
    super(SIGNATURE_REGEXP_EVALUATOR, FILE_NAME_EVALUATOR)

    @path = path ||= "#{File.dirname(__FILE__)}/../../sdk-csharp/build"
  end

  def extract(*args)
    super do |signature|
      signature.squish!

      if signature.size > 80
        match = signature.match(/([<>\w\s\*:]+)\(([\w:&\s,\*<>]*)\)/)

        params = match[2]&.split(', ')&.map { |param| "\n    #{param}" }&.join(", ")
        "#{match[1]}(#{params});\n"
      else
        "#{signature};\n"
      end
    end
  end
end

class SignatureExtractor::Java < SignatureExtractor
  SIGNATURE_REGEXP_EVALUATOR = -> (controller_name, action_name) do
    if action_name == 'constructor'
      /(public .*\s?#{controller_name.underscore.camelcase}\([^{]+)/
    else
      /(public .*\s?#{action_name}\([^{]+)/
    end
  end

  FILE_NAME_EVALUATOR = -> (controller_name) do
    if controller_name == 'websocket'
      'WebSocket.java'
    else
      "#{controller_name.underscore.camelcase}.java"
    end
  end

  def initialize(path = nil)
    super(SIGNATURE_REGEXP_EVALUATOR, FILE_NAME_EVALUATOR)

    @path = path ||= "#{File.dirname(__FILE__)}/../../sdk-java/build/java/io/kuzzle/sdk"
  end

  def extract(*args)
    super do |signature|
      signature.squish!

      if signature.size > 80
        match = signature.match(/([<>\w\s\*:]+)\(([\w:&\s,\*<>]*)\)/)

        params = match[2]&.split(', ')&.map { |param| "\n    #{param}" }&.join(", ")
        "#{match[1]}(#{params});\n"
      else
        "#{signature};\n"
      end
    end
  end
end
