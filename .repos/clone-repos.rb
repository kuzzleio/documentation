require 'yaml'

repositories = YAML.load_file("#{__dir__}/repositories.yml")

repositories.each do |repository|
  cmd = """
git clone \
--depth 10 \
--single-branch \
--branch #{repository['branch']} \
#{repository['url']} \
#{__dir__}/#{repository['destination']}
  """

  puts cmd

  `#{cmd}`
end
