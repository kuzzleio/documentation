

// Updating the profile with a Profile object
kuzzle
  .security
  .fetchProfile("myprofile", opts, new ResponseListener<Profile>() {
    @Override
    public void onSuccess(Profile profile) {

      ArrayList<Profile> profileIds = new ArrayList<Profile>();
      profileIds.add(profile);

      user.setProfiles(profileIds);

    }
  });

// Updating the profile with a profile ID
ArrayList<String> profileIds = new ArrayList<String>();
profileIds.add("myprofile");

user.setProfiles(profileIds);
