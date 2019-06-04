

// Updating the profile with a Profile object
kuzzle
  .security
  .fetchProfile("myprofile", opts, new ResponseListener<Profile>() {
    @Override
    public void onSuccess(Profile profile) {
      // Can add the profile directly with a Profile object
      user.addProfile(profile);
    }
  });

// Updating the profile with a profile ID
user.addProfile("myprofile");
