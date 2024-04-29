# Multi-agent Argumentation-based Decision Framework with Defeasible Preference Rules (MADF with Dpref-rules)

This project is an implementation of a research line carried out by Matías E. Gandolfo, Martín E. Buron Brarda, Luciano H. Tamargo and Alejandro J. García, all of them members of the Institute for Computer Science and Engineering (ICIC, UNS-CONICET).

The application contains two main modules: An interface for the users (developed in Java) and the application core (developed in SWI-Prolog). So, for running our application you will need to install the Java Virtual Machine and the SWI-Prolog interpreter into your computer. The poryect have been tested on Windows 10 with Java Runtime Environment (JRE) 7 and SWI-Prolog 7.6.4 You can download the JRE 7 (64 bits) from: https://www.oracle.com/java/technologies/javase/javase7-archive-downloads.html and SWI-Prolog 7.6.4 (64 bits) from: https://www.swi-prolog.org/download/stable?show=all.

We have tested our application only on machines running Windows. However, since all the the code that we have developed is executed via-interpreter, none major problems should appear for running our application in different operative systems (Linux, MAC OS, etc.). You can download the whole code, or just download the application that was built for Windows x64.

Also, for running our application in Windows it is required to have the SWI-Prolog '/bin' directory added to the %PATH% environment variable. In the following link, there is an instruction manual to do this: https://docs.telerik.com/teststudio/features/test-runners/add-path-environment-variables.


# Launch the application

After downloading and extracting the file app.zip from our latest release (https://github.com/buronbrarda/madf_with_dprefrules/blob/autonomous_vehicles/app.zip), you can launch the application executing the file app.jar using your pre-installed JVM (see the explanation above to verify if you meet all the requirements to execute the application). Also, you can run the application from your command line console excuting > <JRE7 PATH>/bin/java.exe -jar app.jar.

In order to try our application with a default example you can press on Load example, and then press Run. Then, on the right side of the window the selected alternatives for the loaded example will be shown, as well as, other statistical results like the reasoning time, selection time, and argument amount. For more details about this stats you can consult this article: https://doi.org/10.1016/j.eswa.2019.02.021.

To create a new example, or modify an existing one, you can edit the files into the examples folder. Also, you can load new data (criteria, evidence or preference rules) by loading a new .csv file into the application. To do this, press the Edit button on the desired frame, and then load the file with your own data.

At the Explanation graph window:
- Vertices represent alternatives or group of alternatives. 
- Vertices are labeled with the alternatives' id.
- White vertices represent the computed selected alternatives (otherwise are black).
- An arc from v1 to v2 labeled R, means that alternative v1 is preferred to v2 based on the set of rules R.
- For zoom in/out use mouse wheel.
- Vertices can be dragged for your convenience. 
- More than one vertex can be picked to be dragged (they will turn to cyan when picked).
- Keep pressed shift to combine your current selection with a new one.
- Right click on vertices will display a pop-up menu with two items: Expand and Add Extra-Edges.
- Expand will transform a compund vertex with multiple alternatives into multiple vertices.
- Add extra-edges will generate all edges among the selected vertices.
- Right click on arcs will show the explain option, if selected the Delta-Explanation and Argument graph for the selected arcs will be shown.

At the Delta-Explanation graph window:
- Triangles represent arguments.
- Arguments are labeled with their ids.
- Blue arguments represent they were marked as undefeated (defeated arguments are yellow).
- The relations among arguments display the different dialectical trees the justify the anwser of the framework.
- Arguments can be dragged for your convenience.

At the Argument graph window:
- Triangles represent arguments.
- Arguments are labeled with their ids, claim, and rule.
- The relations among the arguments depicts the defeat relation.
- Green arguments represent they are accepted (rejected arguments are red).
- Arguments can be dragged for your convenience.

On graph windows you can find in the top-left corner a menu to change the mouse mode from "picking" to "transforming" and vice-versa.


# Thanks to..

	-- JPL7 (https://jpl7.org/)
	-- JUNG (http://jung.sourceforge.net/)
	-- APACHE.COMMONS.COLLECTIONS (http://commons.apache.org/)
	
They make possible to create this application!
