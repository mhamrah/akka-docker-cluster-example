FROM dockerfile/java

MAINTAINER Michael Hamrah <m@hamrah.com>

ADD tmp/ /opt/app/
ADD start /opt/start
RUN chmod +x /opt/start

EXPOSE 1600

ENTRYPOINT [ "/opt/start" ]
