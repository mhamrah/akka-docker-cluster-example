akka-docker-cluster-example
===========================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/06/akka-clustering-with-sbt-docker-and-sbt-native-packager/). Uses [SBT Native Packager](https://github.com/sbt/sbt-native-packager) and [SBT Docker](https://github.com/marcuslonnberg/sbt-docker) to build docker containers for akka-clusters.

### How to Run

In SBT, just run ```docker``` from sbt-docker. There's a dependency on ```stage``` so sbt-native-packager will be triggered automagically. 

To launch the first node, which will be the seed node:

```
$ docker run -i -t --name seed hamrah.com/clustering
```

To add a member to the cluster:

```
$ docker run --name c1 --link seed:seed -i -t hamrah.com/clustering
```
