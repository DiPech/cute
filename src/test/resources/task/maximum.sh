#!/usr/bin/env bash
#!title: Absolute maximum for creating task
#!description: Execute task "minimum" before and after execution three times.
#!exec-before: minimum
#!exec-after: minimum minimum
#!flag: name=print shortcut=p title="Print something"
#!flag: name=d title="Draw something"
#!param: name=s title="Defines some scope" required validate="[a-z]+"
#!param: name=multi title="Parameter can accepts multiple values" default=["a", "b", "c"] validate="[a-z]" multiple
echo "This task made for demonstrating every aspect of writing tasks for Cute"
echo "Use can define flags and parameters and use it."
echo "For example flag 'print' (or just 'p') and 'd':"
# ... example "if" with variables "print" and "p"
# ... example "if" with variable "d"
echo "For example parameter 's':"
echo "Scope value is: $s"
echo "For example parameter 'multi':"
# ... example "for" with variable "multi" values
