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

    match = content.scan(@sig_regexp_evaluator.call(action))

    if match.nil?
      byebug
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
  SIGNATURE_REGEXP_EVALUATOR = -> (action_name) { /(public .* #{action_name}\([^{]+)/ }
  FILE_NAME_EVALUATOR = -> (controller_name) { "#{controller_name.camelcase}.cs" }

  def initialize(path = nil)
    super(SIGNATURE_REGEXP_EVALUATOR, FILE_NAME_EVALUATOR)

    @path = path ||= "#{File.dirname(__FILE__)}/../../sdk-csharp/build"
  end

  def extract(*args)
    super { |sig| "#{sig.squish};" }
  end
end
