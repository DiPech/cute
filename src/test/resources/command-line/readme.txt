# Regexp to parse command line

We need ability to parse string command to array of strings exact like bash or similar soft does.
Realisation in class CommandLineParser. Here is regexp to parse.

## Common template
((___)|(___)|(___)|(___)|(___)|(___)|(___)|(___))(\s+)?

## Replacements in order
- Long parameter with text inside: --[\w\d]+\=".*?"
- Short parameter with text inside: -[\w\d]\=".*?"
- Long parameter with simple string: --[\w\d]+\=[\w\d-_]+
- Short parameter with simple string: -[\w\d]\=[\w\d-_]+
- Long flag: --[\w\d]+
- Short flags combined: -[\w\d]+
- Short flag: -[\w\d]
- Task name: [\w\d:]+

## Result template:
((--[\w\d]+\=".*?")|(-[\w\d]\=".*?")|(--[\w\d]+\=[\w\d-_]+)|(-[\w\d]\=[\w\d-_]+)|(--[\w\d]+)|(-[\w\d]+)|(-[\w\d])|([\w\d:]+))(\s+)?
