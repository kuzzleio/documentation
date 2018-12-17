$LOAD_PATH.unshift File.dirname(__FILE__)
require 'active_support/all'
require 'byebug'

class Section
  attr_reader :kind, :content, :page

  def initialize(kind, regexp, page)
    @kind, @regexp, @page = kind, regexp, page
  end

  def parse
    match = @page.read_content.match(@regexp)

    return false if match.nil?

    @content = match[1]

    @page.move_forward(@content.length)

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

  def parse
    if super
      @content += "\n\n"
      true
    else
      false
    end
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
  REGEXP = /(^### [\w|\*]+$.+?)^##/m

  def initialize(page)
    super(:argument, REGEXP, page)
  end
end

class Return < Section
  REGEXP = /(^## \w+$[^[##]]+)^##/

  def initialize(page)
    super(:return, REGEXP, page)
  end
end

class Exceptions < Section
  REGEXP = /(^## \w+$[^[##]]+)^##/

  def initialize(page)
    super(:exceptions, REGEXP, page)
  end
end

class Custom < Section
  REGEXP = /(^## [\w&\s]+$[^[##]]+)^##/

  def initialize(page)
    super(:custom, REGEXP, page)
  end
end

class Usage < Section
  REGEXP = /(^## \w+$[^[##]]+)/

  def initialize(page)
    super(:usage, REGEXP, page)
  end
end
