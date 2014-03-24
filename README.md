akka-docker-cluster-example
===========================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/03/running-an-akk…ker-containers/).

### How to Run

Run ```bin/dockerize``` to run sbt native packager to create a distribution and build a docker container named _clustering_.

To launch the first node, which will be the seed node:

```
$ docker run -i -t -name seed clustering
```

To add a member to the cluster:

```
$ docker run -name c1 -link seed:seed -i -t clustering
```
