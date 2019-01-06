$LOAD_PATH.unshift File.dirname(__FILE__)
require 'active_support/all'
require 'byebug'

class Section
  GENERIC_REGEXP = /(^#* [\w\s]+$[^#]*)/
  attr_reader :kind, :content, :page, :title, :up

  def initialize(page, up)
    @page, @up = page, up
  end

  def parse(extract_regexp = GENERIC_REGEXP)
    match = @page.read_content.match(extract_regexp)

    return false if match.nil?

    @content = match[1]

    @page.move_forward(@content.length)

    find_kind

    unless @kind == :header
      match = @content.match(/^#* ([\w\s]+)$/)
      @title =  match[1].gsub!(/\n/, '')
    end

    true
  end

  def find_kind
    if @content.match(/^#* Signature+$/)
      @kind = :signature
    else
      @kind = :section
    end
  end

  def to_s
    @content
  end
end

class Header < Section
  HEADER_REGEXP = /(^---$[^-]+^---$)/

  def parse
    if super(HEADER_REGEXP)
      @content += "\n\n"
      true
    else
      false
    end
  end

  def find_kind
    @kind = :header
  end
end
