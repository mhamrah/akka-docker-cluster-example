akka-docker-cluster-example
===========================

An example akka-cluster project with docker support. See [the blog post](http://blog.michaelhamrah.com/2014/03/running-an-akk…ker-containers/).

### How to Run

Run ```bin/dockerize``` to run sbt native packager to create a distribution and build a docker container named _clustering_.

Run

```
$ docker run -i -t -name seed clustering
```

to launch a seed node and run

```
$ docker run -name c1 -link seed:seed -i -t clustering
```

to add a member to the cluster.

