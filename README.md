# PagingSimulation
Vitural Paging Simulation using LRU algorithm
author: Cody A. Curtis
github: https://github.com/codyacurtis
language: Java (import java.util.Scanner)

PURPOSE
------------------
The purpose of this program is simulate a virtual paging memory manager utilizing a 
Least Recently Used (LRU) algorithm. The program stores the page number along with the index indicating
its most recent use. The LRU algorithm then checkes if the new page to be accessed is already in memory, 
and if so, the algorithm will replace the old index stored with new index making the page the most recent
in memory. The program will show how LRU replaces and stores the pages with their index as it iterates 
the page-refrence entered during the prompt. It will also show the count of page faults at each interation.


HOW TO RUN
------------------
Save the project onto your computer and run on any compiler (eclipsed was used to write and test)
The page reference must be a string of whole integers seperated by a space and ending with -1 or -99.
The program will continue if -1 is entered and will close after the simulation if -99 is entered.
The frames entered must be a whole integer being either 3, 4, or 5.
This program does check for a wide array of potential errors, excluding mismatched data types.

Example Input/Output:
The simulation has now started:
Please enter a page reference string:
1 2 2 3 1 5 6 1 1 4 3 2 2 1 -1
Please enter the number of frames: 5

ReferencePage	Frame1	Frame2	Frame3	Frame4	Frame5	LRU-Q1	LRU-Q2	LRU-Q3	LRU-Q4	LRU-Q5	PF-count
=========================================================================================================
1		|1	|0	|0	|0	|0	|1	|0	|0	|0	|0	|1	|
2		|1	|2	|0	|0	|0	|1	|2	|0	|0	|0	|2	|
2		|1	|2	|0	|0	|0	|1	|3	|0	|0	|0	|2	|
3		|1	|2	|3	|0	|0	|1	|3	|4	|0	|0	|3	|
1		|1	|2	|3	|0	|0	|5	|3	|4	|0	|0	|3	|
5		|1	|2	|3	|5	|0	|5	|3	|4	|6	|0	|4	|
6		|1	|2	|3	|5	|6	|5	|3	|4	|6	|7	|5	|
1		|1	|2	|3	|5	|6	|8	|3	|4	|6	|7	|5	|
1		|1	|2	|3	|5	|6	|9	|3	|4	|6	|7	|5	|
4		|1	|4	|3	|5	|6	|9	|10	|4	|6	|7	|6	|
3		|1	|4	|3	|5	|6	|9	|10	|11	|6	|7	|6	|
2		|1	|4	|3	|2	|6	|9	|10	|11	|12	|7	|7	|
2		|1	|4	|3	|2	|6	|9	|10	|11	|13	|7	|7	|
1		|1	|4	|3	|2	|6	|14	|10	|11	|13	|7	|7	|
=========================================================================================================
Please enter a page reference string:
1 1 2 2 2 3 3 3 4 1 2 5 3 6 5 3 -99
Please enter the number of frames: 4

ReferencePage	Frame1	Frame2	Frame3	Frame4	LRU-Q1	LRU-Q2	LRU-Q3	LRU-Q4	PF-count
========================================================================================
1		|1	|0	|0	|0	|1	|0	|0	|0	|1	|
1		|1	|0	|0	|0	|2	|0	|0	|0	|1	|
2		|1	|2	|0	|0	|2	|3	|0	|0	|2	|
2		|1	|2	|0	|0	|2	|4	|0	|0	|2	|
2		|1	|2	|0	|0	|2	|5	|0	|0	|2	|
3		|1	|2	|3	|0	|2	|5	|6	|0	|3	|
3		|1	|2	|3	|0	|2	|5	|7	|0	|3	|
3		|1	|2	|3	|0	|2	|5	|8	|0	|3	|
4		|1	|2	|3	|4	|2	|5	|8	|9	|4	|
1		|1	|2	|3	|4	|10	|5	|8	|9	|4	|
2		|1	|2	|3	|4	|10	|11	|8	|9	|4	|
5		|1	|2	|5	|4	|10	|11	|12	|9	|5	|
3		|1	|2	|5	|3	|10	|11	|12	|13	|6	|
6		|6	|2	|5	|3	|14	|11	|12	|13	|7	|
5		|6	|2	|5	|3	|14	|11	|15	|13	|7	|
3		|6	|2	|5	|3	|14	|11	|15	|16	|7	|
========================================================================================
See you next time!

This program does not require any special API's or external packages.

LIMITATIONS/CONSTRAINTS
------------------
Assumptions have limited this program to a max of 20 page-references at a time.
A max number of 9 pages can be ran with a single run of this program.
Frames entered must be a an integer and must be 3, 4, or 5.
No text may be entered in this program.

ACKNOWLEDGEMENT
------------------
N/A
