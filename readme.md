[![Build Status](https://travis-ci.com/DiPech/cute.svg?branch=develop)](https://travis-ci.com/DiPech/cute)
[![codecov](https://codecov.io/gh/DiPech/cute/branch/develop/graph/badge.svg)](https://codecov.io/gh/DiPech/cute)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
# Cute – Makefile alternative written in Java

## Предисловие

На данный момент этот проект является демонстрационным – для демонстрации как можно разрабатывать проекты используя TDD методологию. 

Изначально этот проект действительно предназначался для того, чтобы решить задачу стандартизации и повторного использования bash-кода в собственных проектах.
Но решил не доводить решение до production-ready по многим причинам.

## Что под капотом

Консольное приложение, написанное с использованием:
- Spring Boot
- Lombok
- Apache Commons
- JUnit

Значительная часть кода – покрыта тестами (см. бейджик).

## Описание проекта

Предполагалось сделать annotation-based расширение для bash-скриптов, привносящее дополнительные возможности:
- Возможность хранить набор связанных и структурированных bash скриптов раздельно, но использовать вместе путём комбинации
(в итоге получались бы общие библиотеки bash скриптов для разных нужд, а также специфичные для каждого проекта).
- Стандартизация входных данных и способа их передачи в bash скрипты
(например: только так можно передавать флаги и параметры `cute scope:cmd -f1 --flag2 -p1=value1 --parameter2=value2`).
- Возможность строить цепочки последовательного выполнения bash скриптов 
(например: команда `cute docker:rm` могла бы вызывать под капотом `cute docker:rm:containers docker:rm:volumes --force docker:rm:images --all`).

Пример bash команды может быть найден в тестовых ресурсах, например: 
[src/test/resources/task/maximum.sh](src/test/resources/task/maximum.sh)

## Старое readme

[Может быть найдено тут](./readme.dev.md)