require 'active_support/all'
require 'byebug'
require 'fileutils'
require 'lex'

class String
  def camelcaselow
    self.split('_').inject([]){ |buffer,e| buffer.push(buffer.empty? ? e : e.capitalize) }.join
  end
end


def each_dir(start, &block)
  directories = Dir["#{start}/*"]

  directories.each do |path|
    if File.file?(path)
      block.call(path)
    else
      each_dir(path, &block)
    end
  end
end

class Section
  attr_reader :kind, :content

  def initialize(kind, regexp, page)
    @kind, @regexp, @page = kind, regexp, page
  end

  def parse
    match = @page.read_content.match(@regexp)
    # byebug

    if match.nil?
      return false
    end

    @content = match[1]

    @page.move_forward(@content.length)
    @page = nil

    true
  end

  def to_s
    @content
  end
end

class Header < Section
  REGEXP = /(^---$[^-]+^---$)/

  def initialize(page)
    super(:header, REGEXP, page)
  end
end

class Description < Section
  REGEXP = /(^# \w+$[^#]+)/

  def initialize(page)
    super(:description, REGEXP, page)
  end
end

class Signature < Section
  REGEXP = /(^## \w+$[^[##]]+)^##/

  def initialize(page)
    super(:signature, REGEXP, page)
  end
end

class Arguments < Section
  REGEXP = /(^## \w+$[^[###]]+)^###/

  def initialize(page)
    super(:arguments, REGEXP, page)
  end
end

class Argument < Section
  REGEXP = /(^### [\w|\*]+$[^[##]]+)^##/

  def initialize(page)
    super(:argument, REGEXP, page)
  end
end

class Exceptions < Section
  REGEXP = /(^## \w+$[^[##]]+)^##/

  def initialize(page)
    super(:exceptions, REGEXP, page)
  end
end

class Page
  attr_reader :path, :sdk, :version, :controller, :action, :sections

  def initialize(path)
    @path = path
    @content = File.read(@path)
    @cursor = 0

    parts = @path.split('/')
    @sdk = parts[2]
    @version = parts[3]
    @controller = parts[4]
    @action = parts[5]

    @sections = []
  end

  def parse
    @sections << extract_header
    @sections << extract_description
    @sections << extract_signature
    @sections << extract_arguments
    while argument = extract_argument
      @sections << argument
    end
    @sections << extract_exceptions
    byebug
  end

  def read_content
    @content[@cursor..-1]
  end

  def move_forward(count)
    @cursor += count
  end

  private

  def extract_header
    section = Header.new(self)
    if section.parse
      section
    else
      nil
    end
  end

  def extract_description
    section = Description.new(self)
    if section.parse
      section
    else
      nil
    end
  end

  def extract_signature
    section = Signature.new(self)
    if section.parse
      section
    else
      nil
    end
  end

  def extract_arguments
    section = Arguments.new(self)
    if section.parse
      section
    else
      nil
    end
  end

  def extract_argument
    section = Argument.new(self)
    if section.parse
      section
    else
      nil
    end
  end
end

def extract_sections(content)
end

page = Page.new(ARGV[0])
page.parse

byebug

toto = 42
