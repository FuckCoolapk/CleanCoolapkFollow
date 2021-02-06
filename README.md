# CleanCoolapkFollow

Clean your coolapk follow fastly.

# Usage

```shell
> java -jar CoolapkFollow.jar uid userName token fansNumber isOperate
```

- uid: your coolapk account uid
- userName: your coolapk username
- fansNumber: users with less than or equal this number of followers will be unfollowed
- isOperate: whether really do operate or not (true/false)

# Rules

- Exclude authenticated users
- Unfollow if the username contains "酷友"
- Unfollow if the user use the default avatar
- Unfollow users with specified number of fans and below

# License

[WTFPL](LICENSE)
