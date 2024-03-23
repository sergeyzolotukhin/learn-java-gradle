### Best practices for getting code into a container

* Using RUN git clone ... in a Dockerfile and build the image each time the source code changes. 
* Get the source code to the host and use COPY . /whatever in the Dockerfile. 
* Get the source code to the host and use docker run -v $(pwd):/whatever/
* Copy files from host to Docker container.
  https://stackoverflow.com/questions/22907231/how-to-copy-files-from-host-to-docker-container