Currently, it's parsing common verbs from given language codes. More codes could be added later & still need to save those verbs in DB.

Data is parsing using Akka actor model. Multiple instances of SAME actor are created (depending upon language codes) and all HTTP requests are executed in parallel fashion.

TODO: still in progress to parse `ALL` link for each language (e.g. https://cooljugator.com/en/list/all)
