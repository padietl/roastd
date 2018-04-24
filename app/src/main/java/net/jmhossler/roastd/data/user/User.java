package net.jmhossler.roastd.data.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// Description: Data object for a user. Contains unique user id, email, name, photoURL,
//   and favoriteUuids
public class User implements Serializable{

  @NonNull
  private String uuid;

  @NonNull
  private String email;

  @NonNull
  private String name;

  @Nullable
  private String photoURL;

  @NonNull
  private Map<UUID, Boolean> favoriteUUIDs;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(@NonNull String uuid, @NonNull String email,
              @NonNull String name, @Nullable String photoURL) {
    this.setUuid(uuid);
    this.setEmail(email);
    this.setName(name);
    this.setPhotoURL(photoURL);
    this.favoriteUUIDs = new HashMap<>();

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else if (obj == null) {
      return false;
    } else if (obj instanceof User) {
      if (getUuid().equals(((User) obj).getUuid())) {
        return true;
      }
    }
    return false;
  }

  @NonNull
  public String getUuid() {
    return uuid;
  }

  public void setUuid(@NonNull String uuid) {
    this.uuid = uuid;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  @Nullable
  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(@Nullable String photoURL) {
    this.photoURL = photoURL;
  }

  @NonNull
  public Map getFavoriteUUIDs() {
    return favoriteUUIDs;
  }

  public void setFavoriteUUIDs(@NonNull Map favoriteUUIDs) {
    this.favoriteUUIDs = favoriteUUIDs;
  }

  public void addFavoriteUUID(@NonNull UUID favorite) {
    this.favoriteUUIDs.put(favorite, true);
  }

  public void removeFavoriteUUID(@NonNull UUID favorite) {
    this.favoriteUUIDs.remove(favorite);
  }
}
