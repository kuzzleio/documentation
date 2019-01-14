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
    @content = @page.read_content

    replace_anchors

    match = @content.match(extract_regexp)

    return false if match.nil?

    @content = match[1]

    replace_anchors(reverse: true)

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

  private

  # Anchors breaks the regexp because of the '#'
  def replace_anchors(reverse: false)
    links = @content.scan(/(<a.*<\/a>)|(\[[\w\s]+\]\((\{\{ \w+ \}\})?[\w\/\:\.#-]+\))/)

    args = ['#', 'i_am_the_anchor_now']
    args = args.reverse if reverse

    links.flatten.compact.each do |link|
      new_link = link.gsub(*args)
      @content.gsub!(link, new_link)
    end
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
