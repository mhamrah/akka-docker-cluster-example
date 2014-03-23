# Create a container for running akka clustering apps
#
# Uploads sbt native packher output into a container
# and sets appropriate environment variables for
# seed node discovery

# Use a trusted java base
FROM dockerfile/java

MAINTAINER Michael Hamrah <m@hamrah.com>

# Add the tmp/ folder created via bin/dockerize
ADD tmp/ /opt/app/

# Add the start script and set to execute
ADD start /opt/start
RUN chmod +x /opt/start

# The default akka clustering port set via akka.remote.netty.tcp.port
EXPOSE 1600

# Use entrypoint to treat this like an executable; makes command line flags easier
ENTRYPOINT [ "/opt/start" ]
