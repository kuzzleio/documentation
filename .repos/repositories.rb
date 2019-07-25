require 'yaml'

def each_repository
  repositories = YAML.load_file("#{__dir__}/repositories.yml")

  repositories.each do |repository|
    yield repository
  end
end

def clone_repos(branch = 'stable')
  each_repository do |repository|
    clone_command = """
git clone \
--depth 10 \
--single-branch \
--branch #{repository[branch]} \
#{repository['url']} \
#{__dir__}/#{repository['destination']}
    """

    puts clone_command
    system clone_command
  end
end

def clone_repo(repo)
  each_repository do |repository|
    next unless repository['destination'].end_with?(repo)

    clone_command = """
git clone \
--depth 10 \
--single-branch \
--branch #{repository['branch']} \
#{repository['url']} \
#{__dir__}/#{repository['destination']}
    """

    puts clone_command
    system clone_command
  end
end

def command_repos(cmd)
  each_repository do |repository|
    command = """
cd #{__dir__}/#{repository['destination']} && \
#{cmd}
  """

    puts command
    system command
  end
end

def update_repos
  each_repository do |repository|
    update_command = """
cd #{__dir__}/#{repository['destination']} \
&& git pull origin #{repository['branch']}
    """

    puts update_command
    system update_command
  end
end

def remove_repos
  each_repository do |repository|
    remove_command = "rm -rf #{__dir__}/#{repository['destination']}"

    puts remove_command
    system remove_command
  end
end

case ARGV[0]
when 'command'
  command = ARGV[1]
  command_repos(command)
when 'clone-single'
  repo = ARGV[1]
  clone_repo(repo)
when 'clone'
  branch = ARGV[1]
  clone_repos(branch)
when 'update'
  update_repos
when 'remove'
  remove_repos
else
  puts 'Invalid command. Use "clone", "clone-single", "update" or "remove".'
end