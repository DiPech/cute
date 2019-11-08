#!/usr/bin/env bash
#!title: Some title
#!description: Some so long and
# multiline description.
#  It's also can be super multilined.
#!exec-before: minimum
#     minimum
# minimum
#!exec-after: minimum        minimum                  minimum
#!flag: name=print shortcut=p title="Print something"
#!flag: name=d title="Draw something"
#!param: name=s title="Defines some scope" required validate="[a-z]+"
#!param: name=multi title="Parameter can accepts multiple values"
#        default=["a", "b", "c"] validate=“[a-z]" multiple
echo "Example echo to stop parsing comment lines"
