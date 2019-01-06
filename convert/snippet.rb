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
    # Handle controller/action/snippets/snippet.cpp and
    # section/snippets/snippet.cpp
    controller_section = parts.size == 8

    @root = parts[0..1].join('/')
    @sdk = parts[2]
    @version = parts[3]
    @controller = parts[4]
    if controller_section
      @action = parts[5]
      @snippet = parts[7].split('.').first
    else
      @action = ""
      @snippet = parts[6].split('.').first
    end
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

    return if @controller.in?(['getting-started'])

    snippet_mutator = "SnippetMutator::#{sdk.camelcase}".constantize.new
    new_snippet_content = snippet_mutator.mutate(content)

    FileUtils.mkdir_p(dest_path)
    File.write("#{dest_path}/#{snippet}.cs", new_snippet_content)
  end

end
