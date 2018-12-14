$LOAD_PATH.unshift File.dirname(__FILE__)
require 'active_support/all'
require 'byebug'
require 'fileutils'


class TestFile
  attr_reader :path, :sdk, :version, :controller, :action, :snippet

  def initialize(path)
    @path = path.gsub(/\/\//, '/')

    parts = @path.split('/')
    @root = parts[0..1].join('/')
    @sdk = parts[2]
    @version = parts[3]
    @controller = parts[4]
    @action = parts[5]
    @snippet = parts[7]
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

    new_snippet_content = File.read(@path)
    new_snippet_content.gsub!('sdk: cpp', "sdk: #{sdk}")
    new_snippet_content.gsub!('version: 1', "version: #{version}")

    FileUtils.mkdir_p(dest_path)
    File.write("#{dest_path}/#{snippet}", new_snippet_content)
  end

end
