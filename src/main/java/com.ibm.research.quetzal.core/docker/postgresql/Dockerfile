FROM ubuntu

MAINTAINER Richard Schiller <r.schiller@elsevier.com>
 
RUN apt-get update && apt-get install -y python-software-properties software-properties-common postgresql postgresql-client postgresql-contrib \
&& apt-get install -y default-jdk \
&& apt-get install -y maven \
&& apt-get install -y git \
&& apt-get install -y gawk \
&& apt-get install -y realpath \
&& apt-get install -y vim build-essential sudo ant gradle net-tools iputils-ping openssh-server systemd libpam-systemd systemd-gui \
&& mkdir /data && chown -R postgres /data

# add postgres to the sudoers group
RUN echo "postgres ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers

# fixes the login kickout after getting keys granted
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd
RUN echo "AuthorizedKeysFile %h/.ssh/authorized_keys" >> /etc/ssh/sshd_conf
RUN echo "IgnoreUserKnownHosts yes" >> /etc/ssh/sshd_conf
RUN service ssh restart 

# RJS ENHANCEMENT FOR UBUNTU 16 which sets up the java repository for JAVA 8
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update

# Adjust PostgreSQL configuration so that remote connections to the database are possible. 
RUN echo "host all all 0.0.0.0/0 md5" >> /etc/postgresql/9.5/main/pg_hba.conf
# And add `listen_addresses` to `/etc/postgresql/9.5/main/postgresql.conf`
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.5/main/postgresql.conf
# Expose the PostgreSQL port
EXPOSE 5432

# update java to Oracle!
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && apt-get install -y oracle-java8-installer

USER postgres

RUN mkdir ~/.ssh
RUN chmod 755 ~/.ssh
RUN echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCcI2+VEcqrSRpigUW+At/f9C0V5oZxrPNnQdZyP5sks4d3Wa1bgUPfwGSHpt+ZIgWhHetC72MFU2HNNB/3UETmktoGohSHKlAA4NSryMEHc1AxMTFQCsyi718p+g2rVB0dj6n9MJERet1mLsUnu6O/fTTXVscWDvSGYVMezpvnX9bnuYrvHqc+YjF+TvYjnqkDIWweEo2vncn6CiZmKHN+E8Jn+zgaxRlTHbpwHaS6lfrpktSd6opOQNw/GoMlkNv9Y8tmPXc3AWoVvR1lvuJny1rMjvIhxZJpljfqY+X7SWy2uKoBJaA3MMuLO0AbmvMg18E3kWUPqbUT+p0dQ8F/ postgres@957ccad1c5de" >> ~/.ssh/authorized_keys
RUN chmod 644 ~/.ssh/authorized_keys

# This image does nothing much other than set a volume where the data directory # is: i.e., where the nt file sits.  For now the assumption is that
# its unzipped and it exists in the /data dir.  All temporary files get written 
# to that directory.

WORKDIR /data

# Clone the quetzal repository
#    it was in https://github.com/Quetzal-RDF/quetzal
RUN /etc/init.d/postgresql start \
&& git clone https://github.com/rjschiller/quetzal.git \
&& cd /data/quetzal/com.ibm.research.quetzal.core/ \ 
&& mvn install:install-file -Dfile=./mvnLibs/research/iodt.sor/1.0/iodt.sor-1.0.jar -DpomFile=./mvnLibs/research/iodt.sor/1.0/iodt.sor-1.0.pom \
&& mvn install:install-file -Dfile=./mvnLibs/research/hashmvn/hashmvn/1.1/hashmvn-1.1.jar -DgroupId=com.ibm.research.hashmvn -DartifactId=hashmvn -Dversion=1.1 -Dpackaging=jar \
&&  mvn install:install-file -Dfile=./mvnLibs/research/helixhashes/1.0/helixhashes-1.0.jar -DgroupId=com.ibm.research.hashmvn -DartifactId=hashmvn -Dversion=1.1 -Dpackaging=jar \
&& mvn verify -DskipTests

# to connect to this afterward you follow this command
# docker images
# docker run -it <container id> /bin/bash
