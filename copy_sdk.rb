require 'active_support/all'
require 'byebug'
require 'fileutils'

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

def create_snippet_files(path, dest_dir, controller, method)
end

def create_files(path, dest_dir, controller, method)
  snippet_dir = "#{dest_dir}/snippets"
  index_file = "#{dest_dir}/index.md"
  index_content = File.read(path)
  human_controller = controller.gsub('-', '_').camelcase
  human_method = method.gsub('-', '_').camelcaselow

  puts dest_dir

  # make dir
  FileUtils.mkdir_p dest_dir
  FileUtils.mkdir_p snippet_dir

  # create controller index
  controller_index_file = "#{dest_dir.split('/')[0..-2].join('/')}/index.md"
  unless File.exists?(controller_index_file)
    controller_index =
    %{---
layout: sdk.html.hbs
title: #{human_controller}
description: #{human_controller} documentation
---
    }
    File.write(controller_index_file, controller_index)
  end

  # replace metalsmith header
  header = %{---
layout: sdk.html.hbs
title: #{human_method}
description: #{human_controller}:#{human_method}#{method == "constructor" ? "\norder: 1" : ""}
---
  }
  index_content.gsub!(/\-\-\-.*title:\s*\w*\n\-\-\-/m, header)

  # Remove snippets
  count = 1
  snippets = []
  while match = index_content.match(/```js\n(.*?)```/m) do
    snippet = "#{method}-#{count}.js"
    if controller.downcase == "essentials"
      index_content.sub!(/```js\n(.*?)```/m, "[snippet=#{method}-#{count}]")
    else
      index_content.sub!(/```js\n(.*?)```/m, "")
    end
    snippets << "[snippet=#{method}-#{count}]"
    File.write("#{snippet_dir}/#{snippet}", match[1])
    count += 1
  end
  index_content.gsub!(/```java(.*?)```/m, '')
  index_content.gsub!(/```php(.*?)```/m, '')

  # Description
  index_content.gsub(/# fetchNext(.*)\-\-\-/m, '')

  # Add Usage section
  if snippets.any? && controller.downcase != "essentials"
    index_content += %{
## Usage

#{snippets.join("\n\n")}
}
  end

  # Move callback response
  callback_response = index_content.match(/> Callback response:?\n\n```json.*?```/m)
  if callback_response
    index_content.gsub!(/> Callback response:?\n\n```json.*?```/m, "")
    index_content += callback_response[0]
  end

  # Remove new line
  index_content.gsub!(/\n{3,}/m, "\n")

  # Replace templates
  index_content.gsub!("side-code.html.hbs", "sdk.html.hbs")
  index_content.gsub!("full.html.hbs", "sdk.html.hbs")
  index_content.gsub!("category-members.html.hbs", "sdk.html.hbs")

  File.write(index_file, index_content)
end

start = ARGV[0]

each_dir(start) do |path|
  controller = path.split('/')[-2]
  method = path.split('/')[-1].gsub('.md', '')
  method = "constructor" if method == "index"
  dest_dir = "./src/sdk-reference/js/5/#{controller}/#{method}"

  if controller.present?
    create_files(path, dest_dir, controller, method)
  else
    puts path
  end
end
