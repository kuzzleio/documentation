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
      controller,
      action
    ].join('/')

    section_mutator = "SectionMutator::#{sdk.camelcase}".constantize.new

    if @action.end_with?('.md')
      FileUtils.mkdir_p(dest_path.split('/')[0..-2].join('/'))
      return File.write(dest_path, section_mutator.content_mutator(@content))
    end


    new_page_content = @sections.map do |section|
      content = section_mutator.mutate(section)
    end.join

    FileUtils.mkdir_p(dest_path)
    File.write("#{dest_path}/index.md", new_page_content)
  end

  def parse
    @sections << extract_section(:header)
    @sections << extract_section(:description)
    @sections << extract_section(:signature)
    @sections << extract_section(:arguments)
    while argument = extract_section(:argument)
      @sections << argument
    end
    @sections << extract_section(:custom) if section_custom?(/## Getter & Setter/)
    @sections << extract_section(:return) if section_return?
    @sections << extract_section(:exceptions)
    @sections << extract_section(:usage)

    @sections.compact!
  end

  def read_content
    @content[@cursor..-1]
  end

  def move_forward(count)
    @cursor += count
  end

  private

  def extract_section(section_name)
    section = section_name.to_s.capitalize.constantize.new(self)
    if section.parse
      section
    else
      nil
    end
  end

  def section_return?
    ! @content.match(/## Return/).nil?
  end

  def section_custom?(regexp)
    ! @content.match(regexp).nil?
  end
end
