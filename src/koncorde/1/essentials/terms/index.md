---
layout: full.html.hbs
title: Filter Terms
description: Filtering terms rundown
order: 200
---

# Filter Terms

Filters in Koncorde are constituted of terms and operands. In this section, you will find an exhaustive listing of all 
the available terms. Terms allow you to express a predicate to apply on a data stream: if the data matches the filter,
Koncorde will execute the registered callback. One term can constitute a filter on its own or be combined
with other terms in the same filter using the [operands]({{ site_base_path }}koncorde/1/essentials/operands).


## equals

{{{since "1.0.0"}}}

Matches attributes using strict equality.  
The tested attribute must be a scalar (number, string or boolean), and of the same type than the provided filter value.

### Syntax

```
equals: {
  <field name>: <value>
}
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace'
}
```

The following filter validates the first document:

```javascript
{
  equals: {
    firstName: 'Grace'
  }
}
```


## exists

{{{since "1.0.0"}}}

Test for the existence of a key in an object, or of a scalar in an array.  

### Syntax

Since Koncorde 1.2, the `exists` syntax is as follows:

`exists: 'nested.field.path'`
(see [nested field syntax]({{ site_base_path }}koncorde/1/essentials/advanced/#testing-nested-fields-default))

`exists: 'nested.array[value]'`
(see [array value syntax]({{ site_base_path }}koncorde/1/essentials/advanced/#matching-array-values-default))

The following syntax is deprecated since Koncorde 1.2, and supported for backward compatibility only:

`exists: { field: 'nested.field.path' }`

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  city: 'NYC',
  hobby: ['compiler', 'COBOL'],
  alive: false
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  city: 'London',
  hobby: ['programming', 'algorithm']
}
```

The following filter validates the first document:

```javascript
{
  exists: 'alive'
}
```

And this filter validates the second document:

```javascript
{
  exists: 'hobby["algorithm"]'
}
```


## geoBoundingBox

{{{since "1.0.0"}}}

Filter documents containing a geographical point confined within a bounding box:

![Illustration of geoBoundingBox]({{ site_base_path }}assets/images/geolocation/geoBoundingBox.png)

A bounding box is a 2D box that can be defined using either of the following formats:

* 2 [geopoints]({{ site_base_path }}koncorde/1/essentials/geofencing/#geopoints-default/), defining the top left (`topLeft` or `top_left`) and bottom right (`bottomRight` or `bottom_right`) corners of the box
* 4 distinct values defining the 4 box corners: `top` and `bottom` are latitudes, `left` and `right` are longitudes

The bounding box description must be stored in an attribute, named after the geographical point to be tested in future documents.

### Syntax

```
geoBoundingBox: { 
  <geopoint field name>: {
    <bounding box description>
  } 
}
```

### Bounding box description

All syntaxes below are accepted, as they describe the same bounding box, with the following properties:

* top-left corner of latitude `43.5810609` and longitude `3.8433703`
* bottom-right corner of latitude `43.6331979` and longitude `3.9282093`


```javascript
{
  point: {
    top: 43.5810609,
    left: 3.8433703,
    bottom: 43.6331979,
    right: 3.9282093
  }
}
```

```javascript
{
  point: {
    topLeft: { lat: 43.5810609, lon: 3.8433703 },
    bottomRight: { lat: 43.6331979, lon: 3.9282093 }
  }
}
```

```javascript
{
  point: {
    top_left: "43.5810609, 3.8433703",
    bottom_right: "43.6331979, 3.9282093"
  }
}
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  location: {
    lat: 32.692742,
    lon: -97.114127
  }
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: {
    lat: 51.519291,
    lon: -0.149817
  }
}
```

The following filter will match the second document only:

```javascript
{
  geoBoundingBox: {
    location: {
      top: -2.939744,
      left: 52.394484,
      bottom: 1.180129,
      right: 51.143628
    }
  }
}
```

## geoDistanceRange

{{{since "1.0.0"}}}

Filter documents containing a geographical point, whose position is within a distance range from a given point of origin:

![Illustration of geoDistanceRange]({{ site_base_path }}assets/images/geolocation/geoDistanceRange.png)

A `geoDistanceRange` filter contains the following properties:

* a [geopoint]({{ site_base_path }}koncorde/1/essentials/geofencing/#geopoints-default/) defining the center point of the distance range. This geopoint attribute must be named after the geographical point to test in future documents
* a `from` attribute, describing the minimum distance from the center point, using a [geodistance format]({{ site_base_path }}koncorde/1/essentials/geofencing/#geodistances-default/)
* a `to` attribute, describing the maximum distance from the center point, using a [geodistance format]({{ site_base_path }}koncorde/1/essentials/geofencing/#geodistances-default/)

### Syntax

```
geoDistanceRange: {
  <geopoint field name>: {
    <geopoint description>
  },
  from: <geodistance>,
  to: <geodistance>
}
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  location: {
    lat: 32.692742,
    lon: -97.114127
  }
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: {
    lat: 51.519291,
    lon: -0.149817
  }
}
```

The following filter will match the second document only:

```javascript
{
  geoDistanceRange: {
    location: [51.5029017, -0.1606903],
    from: '1km',
    to: '10 kilometers'
  }
}
```

## geoDistance

{{{since "1.0.0"}}}

Filter documents containing a geographical point, whose position is within a distance radius centered around a provided point of origin:

![Illustration of geoDistance]({{ site_base_path }}assets/images/geolocation/geoDistance.png)

A `geoDistance` filter contains the following properties:

* a [geopoint]({{ site_base_path }}koncorde/1/essentials/geofencing/#geopoints-default/) defining the point of origin. This geopoint attribute must be named after the geographical point to test in future documents
* a `distance` parameter in [geodistance format]({{ site_base_path }}koncorde/1/essentials/geofencing/#geodistances-default/)

### Syntax

```
geoDistance: {
  <geopoint field name>: {
    <geopoint description>
  },
  distance: <geodistance>
}
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  location: {
    lat: 32.692742,
    lon: -97.114127
  }
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: {
    lat: 51.519291,
    lon: -0.149817
  }
}
```

The following filter will match the second document only:

```javascript
{
  geoDistance: {
    location: {
      lat: 51.5029017,
      lon: -0.1606903
    },
    distance: '10km'
  }
}
```

## geoPolygon

{{{since '1.0.0'}}}

Filter documents containing a geographical point, confined within a polygon that has an arbitrary number of sides:

![Illustration of geoPolygon]({{ site_base_path }}assets/images/geolocation/geoPolygon.png)

A `geoPolygon` filter is described using a `points` array, containing an arbitrary number of [geopoints]({{ site_base_path }}koncorde/1/essentials/geofencing/#geopoints-default/) (at least 3).  

Koncorde automatically closes geopolygons.

Different geopoint formats can be used to describe different corners of a polygon.

### Syntax

```
geoPolygon: {
  <geopoint field name>: {
    points: <geopoints array>
  }
}
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  location: {
    lat: 32.692742,
    lon: -97.114127
  }
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  location: {record
    lat: 51.519291,
    lon: -0.149817
  }
}
```

The following filter will match the second document only:

```javascript
{
  geoPolygon: {
    location: {
      points: [
        { lat: 51.523029, lon: -0.160793 },
        [51.522842, -0.145043],
        '51.518303, -0.146116',
        { latLon: {lat: 51.516487, lon: -0.162295 }},
        'gcpvh6uxh60x1'
      ]
    }
  }
}
```

## ids

{{{since "1.0.0"}}}

This filter returns only documents having their unique document ID listed in the provided list.

### Syntax

`ids: <array of strings>`

### Example

Given the following documents:

```javascript
{
  _id: 'a',
  firstName: 'Grace',
  lastName: 'Hopper'
},
{
  _id: 'b',
  firstName: 'Ada',
  lastName: 'Lovelace'
},
{
  _id: 'c',
  firstName: 'Marie',
  lastName: 'Curie'
}
```

The following filter validates first document:

```javascript
{
  ids: {
    values: ['a']
  }
}
```

## missing

{{{since "1.0.0"}}}

A filter matching documents either with a missing field in an object, or with a missing value in an array.

A `missing` filter used to match arrays without a specific value will also match if:

* the tested array property is entirely missing from the provided document
* the tested property in the provided document is not an array

### Syntax

Since Koncorde 1.2, the `missing` syntax is as follows:

`missing: 'nested.field.path'`
(see [nested field syntax]({{ site_base_path }}koncorde/1/essentials/advanced/#testing-nested-fields-default))

`missing: 'nested.array[value]'`
(see [array value syntax]({{ site_base_path }}koncorde/1/essentials/advanced/#matching-array-values-default)

The following syntax is deprecated since Koncorde 1.2, and supported for backward compatibility only:

`missing: { field: 'nested.field.path' }`

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper',
  city: 'NYC',
  hobbies: ['compiler', 'COBOL'],
  alive: false
},
{
  firstName: 'Ada',
  lastName: 'Lovelace',
  city: 'London',
  hobbies: ['algorithm', 'programming'],
}
```

The following filter validates the second document:

```javascript
{
  missing: 'alive'
}
```

And this filter validates the first document: 

```javascript
{
  missing: 'hobbies["algorithm"]'
}
```

## range

{{{since "1.0.0"}}}

Filters documents with number attributes within a provided interval.

A range can be defined with at least one of the following arguments:

* `gte`: Greater-than or equal to `<number>`
* `gt`: Greater-than `<number>`
* `lte`: Less-than or equal to
* `lt`: Less-than

Ranges can be either bounded or half-bounded.

### Syntax 

```
range: {
  <field to be tested>: {
    [gte]: <number>,
    [gt]: <number>,
    [lte]: <number>,
    [lt]: <number>
  }
}
```

### Example

Given the following documents:

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

The following filter validates the last two documents:

```javascript
{
  range: {
    age: {
      lt: 85
    }
  }
}
```

## regexp

{{{since "1.0.0"}}}

The `regexp` filter matches attributes using [PCREs](https://en.wikipedia.org/wiki/Perl_Compatible_Regular_Expressions).

### Syntax

A `regexp` filter has the following structure, splitting the usual `/pattern/flags` into two parts:

```javascript
regexp: {
  <field name>: {
    value: '<search pattern>',
    flags: '<modifier flags>'
  }
}
```

If you don't need any modifier flag, then you may also use the following simplified form:

```javascript
  regexp: {
    <field name>: '<search pattern>'
  }
```

### Example

Given the following documents:

```javascript
{
  firstName: 'Grace',
  lastName: 'Hopper'
},
{
  firstName: 'Ada',
  lastName: 'Lovelace'
}
```

The following filter validates the first document:

```javascript
{
  regexp: {
    firstName: {
      value: '^g\w+',
      flags: 'i'
    }
  }
}
```
