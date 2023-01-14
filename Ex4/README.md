alonmarko208


=============================
=     File description   =
=============================
packages -
conversion
fileprocessing
fileprocessing.filters
filesprocessing.orders
fileprocessing.orders
fileprocessing.parsing
fileprcessing.sections
sort

.javafiles - 
KBytesToBytes.java - converts k bytes to bytes added as package for convinience so we 
can expand on it as needed in the future.
DirectoryProcessor.java - the main processor class - runs everything
ProcessingExeption.java - exeption handling for the processing process - all 
unique errors will inherit from this class - this class extends the Execption class
TypeOne.java - type I error exception class - the program continues with "default" values as instructed.
TypeTwo.java - Type II error exception class - throws an error message and stops the program.
UsageHandler.java - invalid usage exception class - relates to type 2 errors.
All.java - all filter class
Between.java - class implements the between filter subsection
Contains.java - implements the contains filter!
Executable.java - implements the executable filter
FileFilter.java - implements the filter class.
Filter.java - abstract class for filter
FilterFactory.java - the factory class for all filters!
GreaterThan.java - implements the greater than filter
Hidden.java - the hidden file filter extends filter and implements it.
Not.java - implements NOT suffix
Prefix.java - implements prefix filter
SmallerThan.java - implements smaller than filter
Suffix.java - implement suffix filter
Writable.java - implements writable filter class
BadName.java - exception for bad filter name
NegParameters.java - exception for negative parameters
UnsuitableOrderVals.java - unsuiutable orders exception
UnsuitableParams.java - unsuitable params exception
UnsuitableYesNo.java - unsuitable yes or no parameters exception
Order.java - abstract class for orders - will implement the 
compartor for File objects by its subclasses
OrderAbs.java - class imlements Abs ordering
OrderFactory.java - order class creator.
OrderSize.java - class implements size ordering
OrderType.java - implements type ordering
ReverseSuffix.java - class that implements the reverse suffix for orders
UnsuitableOrder.java - Unsuitable order exeption.
CmdParse.java - The parser of the Commands File.
CmdParsed.java - class for a command file that was parsed.
UnsuitableFormatException.java - exception for files not in the right format
UnsuitableSubSecException.java - exception for sub-sections with unsiutable (unvalid) names.
Sec.java - section class - combines filter and order to organized structure.
MSorter.java - this class implements a merge sort algorithm.



================
=  Design   =
================
first of all there are alot of files - ALOT. but it makes the code super modular. there is a different class for 
every exception 
easily new ones can be added or deleted. there are packages for sort and conversion - if we want to add more 
conversions or more
sort allgorithms, etc.
so i chose to implement everything as subpackages and than create like ex3 a main abstract class that all 
inherit from for easy understanability.
main order, and filter and from that evrything is inherited. and where is needed (filter and order) 
i created a factory file according to the factory design
pattern to "centrealize" the creation of such classes. section does not need a factory since
 it is a very simple concept so only a constructor.
this pattern creates a division between differnet parts of the code which makes it much more understandable.
so the fileprocessing package has filter package that has all filters and order package that has all orders.
and than sections package has one class that creates a section and that section holds - order object and filter object. 
from that the parsed file contains an array of sections so we have a data structure hierarchy which is easily 
understandable and clear.

each part has its specific exception that it needs.
we also use exeption hirarchy - we have the main ones which are - TypeOne, TypeTwo and
 ProcessingException from which we crated alot more
and each part has its relavant messages to forward accordingto the arisen error. 
these basically act as a pipe for errors upstream.

i also used singleton which were recently learned - since there is no need for more than one instance of orders
 - onces created we can give to it several files
to compare even if the same order is called twice or more in the command files.

i also used for the NOT/REVERSE SUFFIX just the normal filter/order and just reveresed the
 results i obtained from it instead of imlementing a completly new
code.
=============================
=       Implementation      =
=============================
explained mostly above.

=============================
=       Questions         =
=============================
Explain design choices in detail - 
	i think i explained it above. the only thing i can add is the hash map part for the line errors
 - seemed like the easiest way to do it without overdoing it and complicating matters.

Exceptions hierarchy - 
	there could be 2 types of exeptions in this exercise - a processing type or usage type.
	 both of them had to be implemented so idid it with 4 main classes
	as already explained above. and in each package subclasess that extend these in order to promote 
	specific end-case scnarios. 
	also used the I/O exception since it is possible we get it. but that already exists so no need to implement. 
	(not sure it was needed to react to that situation)
	but intelliJ suggested and it was not alot of work to add.
	also used in the main ProcessExeption class the serialVersionUID.  did not fully understand it but 
	since when checking on stackoverflow it was suggested. made it protected so all sub-clasess can inherit.


How did you sort your matched files?
    using the mergesort algorithm seen from the first ex in WORKSHOP IN C/C++ - giving it a comparator<> -
	 which is actually an order we implement.
