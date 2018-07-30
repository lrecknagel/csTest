/*
 * Copyright 2018 lre
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package default.cassandratest.cassandra

import default.cassandratest.cassandra.EntityDataStructure.LucasDocument
import default.cassandratest.cassandra.LucasEnums.LucasEntities.{ Addresses, Hobbies }
import default.cassandratest.cassandra.LucasEnums.LucasEntity
import io.getquill.{ CassandraAsyncContext, Literal }

case class EntityTable(table: String)

trait EntityQueries {

  val ctx: CassandraAsyncContext[Literal.type]
  import ctx._

  val queries: Map[LucasEntity, ctx.Quoted[ctx.EntityQuery[LucasDocument]]] = Map(
    Addresses -> quote(querySchema[LucasDocument]("addresses")),
    Hobbies   -> quote(querySchema[LucasDocument]("hobbies"))
  )
}
