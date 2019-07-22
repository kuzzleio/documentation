require 'yaml'

repositories = YAML.load_file('./repositories.yml')

repositories.each do |repository|
  cmd = `git clone \
    --depth 10
    --single-branch \
    --branch #{repository.branch} \
    #{repository.url} \
    #{repository.destination}`

  puts cmd
end
