$LOAD_PATH.unshift File.dirname(__FILE__)
require 'active_support/all'
require 'byebug'
require 'fileutils'
require 'snippet_mutator.rb'


class Snippet
  attr_reader :path, :sdk, :version, :controller, :action, :snippet, :content

  def initialize(path)
    @path = path.gsub(/\/\//, '/')
    @content = File.read(@path)

    parts = @path.split('/')
    @root = parts[0..1].join('/')
    @sdk = parts[2]
    @version = parts[3]
    @controller = parts[4]
    @action = parts[5]
    @snippet = parts[7].split('.').first
  end

  def copy_to(sdk, version)
    dest_path = [
      @root,
      sdk,
      version,
      controller,
      action,
      "snippets"
    ].join('/')

    snippet_mutator = "SnippetMutator::#{sdk.camelcase}".constantize.new
    new_snippet_content = snippet_mutator.mutate(content)

    FileUtils.mkdir_p(dest_path)
    File.write("#{dest_path}/#{snippet}.cs", new_snippet_content)
  end

end
