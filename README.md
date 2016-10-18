akka-docker-cluster-example
===========================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/11/clustering-akka-applications-with-docker-version-3/). Uses [SBT Native Packager](https://github.com/sbt/sbt-native-packager).

### How to Run

In SBT, just run `docker:publishLocal` to create a local docker container. 

To run the cluster, run `docker-compose up`. This will create 3 nodes, a seed and two regular members, called `seed`, `c1`, and `c2` respectively.

While running, try opening a new terminal and (from the same directory) try things like `docker-compose down seed` and watch the cluster nodes respond.
