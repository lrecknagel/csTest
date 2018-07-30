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
import default.cassandratest.cassandra.LucasEnums.LucasEntity
import io.getquill._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class QuillAccessor(dbName: String) extends DateTimeEncodings with EntityQueries {

  lazy val ctx: CassandraAsyncContext[Literal.type] = new CassandraAsyncContext(Literal, dbName)
  import ctx._

  val entities = Set("addresses", "hobbies")

  def createTables(): Future[Set[Unit]] = Future.traverse(entities)(createTableForEntity)

  def createTableForEntity(entity: String): Future[Unit] =
    ctx.executeAction(
      EntitySchema.entityCreateSQL(entity)
    )

  def truncateTableForEntity(table: String): Future[Unit] =
    ctx.executeAction(
      EntitySchema.entityTruncateSQL(table)
    )

  def dropTableForEntity(table: String): Future[Unit] =
    ctx.executeAction(
      EntitySchema.entityDropSQL(table)
    )

  def getDocuments(entity: LucasEntity) = {
    val query: ctx.Quoted[ctx.EntityQuery[LucasDocument]] = queries(entity)
    ctx.run(query)
  }

  def addDocument(entity: LucasEntity, document: LucasDocument): Future[Unit] = {
    val query: ctx.Quoted[ctx.EntityQuery[LucasDocument]] = queries(entity)
    ctx.run(quote {
      query.insert(lift(document))
    })
  }
}
