# yPoints API

In this topic you'll find methods about the yPoints API.

## Getting the central class

    yPointsAPI ypoints = yPointsAPI.api; // returns the API class.

## Getting a player

    Player p = e.getPlayer();
    PlayerPoints player = ypoints.getPlayer(p); // getting the PlayerPoints player.

## Working with points

    double has = player.getPoints(); // returns the amount of player points.
    ypoints.addPoints(player, 1000); // add an amount of points to a player.
    ypoints.setPoints(player, 100); // add an amount of points to a player.
    ypoints.removePoints(player, 10); // remove an amount of points from the player.
