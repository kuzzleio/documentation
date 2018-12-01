---
layout: full.html.hbs
algolia: true
title: Filter Operands
description: Combine multiple terms to create complex filters
order: 600
---


## and

{{{since "1.0.0"}}}

The `and` filter takes an array of filter objects, combining them with AND operands.

### Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  city: 'NYC',
  hobby: 'computer'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  city: 'London',
  hobby: 'computer'
}
```

### The following filter validates the first document:

```javascript
{
  and: [
    {
      equals: {
        city: 'NYC'
      }
    },
    {
      equals: {
        hobby: 'computer'
      }
    }
  ]
}
```


## bool

{{{since "1.0.0"}}}

Returns documents matching a combination of filters.

This operand accepts the following attributes:

* `must` all listed conditions must be `true`
* `must_not` all listed conditions must be `false`
* `should` one of the listed condition must be `true`
* `should_not` one of the listed condition must be `false`

Each one of these attributes are an array of filter objects.

### Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  age: 85,
  city: 'NYC',
  hobby: 'computer'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  age: 36
  city: 'London',
  hobby: 'computer'
},
{
  firstName: 'Marie',
  lastName: 'Curie',
  age: 55,
  city: 'Paris',
  hobby: 'radium'
}
```

### The following filter validates the second document:

```javascript
{
  bool: {
    must : [
      {
        in : {
          firstName : ['Grace', 'Ada']
        }
      },
      {
        range: {
          age: {
            gte: 36,
            lt: 85
          }
        }
      }
    ],
    'must_not' : [
      {
        equals: {
          city: 'NYC'
        }
      }
    ],
    should : [
      {
        equals : {
          hobby : 'computer'
        }
      },
      {
        exists : {
          field : 'lastName'
        }
      }
    ]
  }
}
```

## not

{{{since "1.0.0"}}}

The `not` filter omits the matching data.

### Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  city: 'NYC',
  hobby: 'computer'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  city: 'London',
  hobby: 'computer'
}
```

### The following filter validates the first document:

```javascript
{
  not: {
    equals: {
      city: 'London'
    }
  }
}
```


## or

{{{since "1.0.0"}}}

The `or` filter takes an array containing filter objects, combining them using OR operands.

### Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  city: 'NYC',
  hobby: 'computer'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  city: 'London',
  hobby: 'computer'
},
{
  firstName: 'Marie',
  lastName: 'Curie',
  city: 'Paris',
  hobby: 'radium'
}
```

### The following filter validates the first two documents:

```javascript
{
  or: [
    {
      equals: {
        city: 'NYC'
      }
    },
    {
      equals: {
        city: 'London'
      }
    }
  ]
}
```
