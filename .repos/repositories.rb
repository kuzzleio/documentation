require 'yaml'

def each_repository
  repositories = YAML.load_file("#{__dir__}/repositories.yml")

  repositories.each do |repository|
    yield repository
  end
end

def clone_repos
  each_repository do |repository|
    clone_command = """
git clone \
--depth 10 \
--single-branch \
--branch #{repository['branch']} \
#{repository['url']} \
#{__dir__}/#{repository['destination']}
    """

    puts clone_command
    `#{clone_command}`
  end
end

def update_repos
  each_repository do |repository|
    update_command = """
cd #{__dir__}/#{repository['destination']} \
&& git pull origin #{repository['branch']}
    """

    puts update_command
    `#{update_command}`
  end
end

def remove_repos
  each_repository do |repository|
    remove_command = "rm -rf #{__dir__}/#{repository['destination']}"

    puts remove_command
    `#{remove_command}`
  end
end

case ARGV[0]
when 'clone'
  clone_repos
when 'update'
  update_repos
when 'remove'
  remove_repos
else
  puts 'Invalid command. Use "clone", "update" or "remove".'
end