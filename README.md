# EnviraChat (circa 1996-1997)

When I was 13 years old, back in 1996-1997, I was writing a program
called EnviraChat. This was a multi-player chat program that resembled
an NES Zelda-style game. The idea was that you could walk around a map
and talk to other players (whisper, talk, yell, based on distance).

There were various game-like elements that existed in the world, and new
ones that could be created through "GOD" (the Graphic Object Designer).

I intended to let people own property and build on it, and to have
multiple screens. This was also intended to work as a standalone
application, Java applet, and an Interphaze extension.

Interphaze was my custom web browser/server with an HTTP-like protocol
and alternative to HTML that supported custom Java-backed tags that
could communicate with each other and manage rendering and some basic
layout. It had tabbed browsing, extensions, and favicons, back in 1996.
It was really cool. I even wrote a paper on it for the DARPA invention
fair, which didn't even get any real acknowledgement. Sadly, Interphaze
was lost to time..

Anyway, back to EnviraChat. Some of what I wanted to do was built, but
also lost to time. This Git repository is populated with a backup I
found on an old floppy disk. It's not complete. I remember certain
design decisions I had moved away from, but are still present in this
tree. For example, I had naively thought that drawing sprites via lines
was cheaper than loading images and blitting them, so the code in
`chcs/envirachat/ui/character` is full of line drawing commands. Later,
I moved away from this and into loading images. That code isn't here.

I know I also did more with GOD. I only have a stub for this here with
commented-out buttons and a non-functional Quit button. What I had
built, though, was something that let me load in the map and place a
list of pre-built objects.

That map, by the way, is also primitive in this tree. Just a hard-coded
string with symbols representing objects to place.

It would be amazing to have the latest iteration of the code. Alas. I'm
still pretty thrilled to have even this.

Now, of course, this doesn't run. It depends on `netscape.*` classes,
and I doubt there's anything modern I can use to fill those in.

Also, I started this in late 1996. Java 1.0 came out in early 1996. I
remember using a beta initially, but it was a fairly new language and I
was eager to learn it.

Looking back on this code, I can certainly see some evolution to my
approach to writing code at a young age. I had been writing code since I
was 7, so 6 years at this point, but this and Interphaze were big
projects that taught me a LOT.

Some of the code in here is documented (I was learning javadoc), and
others are not.

There's a mix of coding styles as I went on, as I tried to figure out
how I liked to write code.

Indentation is a mess (I'm not sure what happened here, but a lot of
this doesn't show indentation within functions, while others do -- can I
blame my IDE?).

Speaking of IDEs, I used Kawa back then. An early version, no doubt,
given how young Java was. Some good memories of that IDE.

I had a lot of foundational classes in the `chcs.common` namespace.
"CHCS" was a company I started when I was 10 or 11 to do computer
consulting and software development in town.

A lot of the UI classes were probably written by someone else. I see
copyrights on some, similar coding styles in others. I probably pulled
these down from packages others were working on to help me get started.
I recall my goal being to use these to get going with the idea that I'd
rewrite it all to better fit into my environment's UI better.

I was also learning networking, how to build client/server applications.
This and Interphaze were playgrounds for this work.

I really had big dreams and goals for this project. I moved on at some
point, and I can't remember when or why. The project never reached the
levels of ambition that I had set, but was a major stepping stone to
better things.

So, here's the only remaining copy of EnviraChat, preserved in Git.
It's a mess, and I love it very much. I'm glad it can continue to live
on in some form.
