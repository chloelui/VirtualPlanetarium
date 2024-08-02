# VirtualPlanetarium
This project consists of a virtual planetarium, created using the Java 3D API, that allows users to view the night sky and prominent constellations.

To compile the Java file:

- Enter to the src directory: cd src
- javac -d ../bin  -cp "../lib/vecmath.jar:../lib/jogamp-fat.jar:../lib/j3dcore.jar:../lib/j3dutils.jar" VirtualPlanetarium.java

To run the final program, use the command:

- Enter to the bin directory: cd bin
- java --add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED -cp ".:../lib/vecmath.jar:../lib/jogamp-fat.jar:../lib/j3dcore.jar:../lib/j3dutils.jar" VirtualPlanetarium
