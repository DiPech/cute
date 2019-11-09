# Regexps to parse inputs

Test it in https://regex101.com

## Regexp to parse cute input string

### Common template
((___)|(___)|(___)|(___)|(___)|(___)|(___)|(___))(\s+)?

### Replacements in order
- Long parameter with text inside: --[\w\d]+\=".*?"
- Short parameter with text inside: -[\w\d]\=".*?"
- Long parameter with simple string: --[\w\d]+\=[\w\d-_]+
- Short parameter with simple string: -[\w\d]\=[\w\d-_]+
- Long flag: --[\w\d]+
- Short flags combined: -[\w\d]+
- Short flag: -[\w\d]
- Task name: [\w\d:]+

### Result template:
((--[\w\d]+\=".*?")|(-[\w\d]\=".*?")|(--[\w\d]+\=[\w\d-_]+)|(-[\w\d]\=[\w\d-_]+)|(--[\w\d]+)|(-[\w\d]+)|(-[\w\d])|([\w\d:]+))(\s+)?

------------------------------------------------------------------------------------------------------------------------

## Regexp to parse definition input string (flags and params)

### Common template
((___)|(___)|(___)|(___))(\s+)?

### Replacements in order
- Param with multiple values: [a-z0-9]+=\[.*?\]
- Param without quotes: [a-z0-9]+=[a-z0-9\-]+
- Param with quotes: [a-z0-9]+=".*?"
- Flag: [a-z0-9]+

### Result template:
(([a-z0-9]+=\[.*?\])|([a-z0-9]+=[a-z0-9\-]+)|([a-z0-9]+=".*?")|([a-z0-9]+))(\s+)?
