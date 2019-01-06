$LOAD_PATH.unshift File.dirname(__FILE__)

require 'active_support/all'
require 'byebug'
require 'fileutils'
require 'section.rb'
require 'section_mutator.rb'

class Page
  CONTROLLERS = %w(auth collection document index realtime server)
  CORE_CLASSES = %w(kuzzle protocol search-result user user-right websocket)

  attr_reader :path, :sdk, :version, :controller, :action, :sections

  def initialize(path)
    @path = path.gsub(/\/\//, '/')
    @content = File.read(@path)
    @cursor = 0

    parts = @path.split('/')
    @root = parts[0..1].join('/')
    @sdk = parts[2]
    @version = parts[3]
    @controller = parts[4]
    @action = parts[5]

    @sections = []

    if @controller.in?(CONTROLLERS)
      @kind = :controller
    elsif @controller.in?(CORE_CLASSES)
      @kind = :core_class
    else
      @kind = :other
    end
  end

  def copy_to(sdk, version)
    dest_path = [
      @root,
      sdk,
      version,
      @controller,
      @action
    ].join('/')

    return if @controller.in?(['index.md', 'getting-started'])

    section_mutator = "SectionMutator::#{sdk.camelcase}".constantize.new

    if @action.end_with?('.md')
      FileUtils.mkdir_p(dest_path.split('/')[0..-2].join('/'))
      return File.write(dest_path, section_mutator.content_mutator(@content))
    end

    new_page_content = @sections.map do |section|
      section_mutator.mutate(section)
    end.join

    FileUtils.mkdir_p(dest_path)
    File.write("#{dest_path}/index.md", new_page_content)
  end

  def parse
    @sections << extract_section(nil, Header)

    while next_section = extract_section(@sections.last)
      @sections << next_section
    end
    @sections.compact!
  end

  def read_content
    @content[@cursor..-1]
  end

  def move_forward(count)
    @cursor += count
  end

  private

  def extract_section(up_section, section_klass = Section)
    section = section_klass.new(self, up_section)
    if section.parse
      section
    else
      nil
    end
  end
end
