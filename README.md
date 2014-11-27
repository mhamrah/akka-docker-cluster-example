akka-docker-cluster-example
===========================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/11/clustering-akka-applications-with-docker-version-3/). Uses [SBT Native Packager](https://github.com/sbt/sbt-native-packager).

### How to Run

In SBT, just run ```docker:publishLocal``` to create a local docker container. 

To launch the first node, which will be the seed node:

```
$ docker run -i -t --rm --name seed mhamrah/clustering:0.3
```

To add a member to the cluster:

```
$ docker run --rm --name c1 --link seed:seed -i -t mhamrah/clustering:0.3
```
