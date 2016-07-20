#!/usr/bin/python
import os
import re
import sys
import ast


if len(sys.argv) < 2:
    print "Too few arguments"
    print "\n Pass exactly one arguments :  Name of directory"
    sys.exit(0)
elif len(sys.argv) >2:
    print "Too many arguments"
    print "\n Pass exactly one arguments :  Name of directory"
    sys.exit(0)
else:
    path=sys.argv[1]    

x=len(sys.argv[1])+7


file_list=[]
for root, dirs, files in os.walk(path,topdown=False):
    for fi in files:
        if fi.endswith('.smali'):            
            file_list.append(os.path.join(root,fi))
print file_list

linestring='.line'
for ifile in file_list:
    ofile=ifile+".temp"
    try:
        input_file = open(ifile, 'r')
        with open(ofile,"w") as output_file:    
            for line in input_file:
                if '.line' in line:
                    pass
                else :    
                    output_file.write(line)
        input_file.close()
        os.rename(ofile,ifile)

    except NameError:
        print  "NameError in "+str(ifile)      
    except IOError:
        print "IOError in "+str(ifile)
