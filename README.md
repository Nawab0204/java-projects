# java-projects
Detailed Explanation of Program

This document provides a detailed explanation of the CosmicCampus Java program, including what each part of the code does.
Import and Class Declaration
import java.util.*;
• Imports all utility classes from java.util, such as Scanner, HashMap, ArrayList, UUID, Optional, and Random.
• public class CosmicCampus: Declares the main class of the program. Everything happens inside this class.
SpaceCadet Inner Class (Represents Students)
• private static class SpaceCadet: Defines a nested class to model a space cadet (student).
• Fields:
   - callsign: Name of the cadet.
   - stardustID: A unique string ID.
   - cosmicGrades: Maps enrolled courses to grades.
• Constructor initializes cadet name and ID.
• warpIntoCourse(SpaceCourse course): Enrolls cadet into a course with a warp animation.
• simulateWarpAnimation(): Displays a loading bar animation.
SpaceCourse Inner Class (Represents Courses)
• Models a course (planet).
• Fields:
   - planetCode: Short code of the course.
   - cosmicName: Full course name.
   - gravityCapacity: Max number of cadets.
   - currentAliens: Cadets currently enrolled.
   - totalColonized: Shared counter for enrollments.
• Constructor prints a random sci-fi fact.
• enrollCadet(): Enrolls cadet if there's space.
Main Control Section (Menus and Input)
• Global Variables:
   - cadets: Stores cadets using their stardustID.
   - galaxy: List of available courses.
• main(): Entry point of the program. Handles menu and commands.
• addPlanet(): Adds a new course (planet) to the galaxy.
• recruitCadet(): Creates and enrolls a new cadet.
• beamGrade(): Assigns a grade and displays feedback.
• printAsciiTitle(): Prints the title (placeholder or ASCII art).
Summary Table: What Does What
Code Section	Purpose
import java.util.*	Gives access to collections, Scanner, Random, UUID, etc.
SpaceCadet class	Models a student (name, ID, courses/grades)
warpIntoCourse()	Enrolls a cadet in a course with animation
simulateWarpAnimation()	Displays a fun loading effect
SpaceCourse class	Models a course with limited capacity
enrollCadet()	Adds cadet to course if there's room
generateSpaceFact()	Returns a random fun message
cadets and galaxy	Store all cadets and all courses
main()	Main loop for showing menu and handling input
addPlanet()	Adds a new course to the galaxy
recruitCadet()	Creates and enrolls a new space cadet
beamGrade()	Assigns a grade and gives feedback
printAsciiTitle()	Prints title art or placeholder

