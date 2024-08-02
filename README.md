# VirtualPlanetarium
This project consists of a virtual planetarium, created using the Java 3D API, that allows users to view the night sky and prominent constellations.

Make the following changes to reflect your local environment:

- Go to model > CelestialSphere_nasa.mtl > line 12 and change the path name of starmap_2020_4k_2.png to your own directory
- Go to src > VirtualPlanetarium.java > line 66 and change the path name of CelestialSphere_nasa.obj to your own directory

To compile the Java file:

- In your terminal, enter to the src directory:
- cd VirtualPlanetarium
- cd src
- javac -d ../bin  -cp "../lib/vecmath.jar:../lib/jogamp-fat.jar:../lib/j3dcore.jar:../lib/j3dutils.jar" VirtualPlanetarium.java

To run the final program:

- In your terminal, enter to the bin directory:
- cd bin
- java --add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED -cp ".:../lib/vecmath.jar:../lib/jogamp-fat.jar:../lib/j3dcore.jar:../lib/j3dutils.jar" VirtualPlanetarium
